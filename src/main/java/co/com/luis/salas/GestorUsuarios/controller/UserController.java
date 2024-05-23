package co.com.luis.salas.GestorUsuarios.controller;

import co.com.luis.salas.GestorUsuarios.model.User;
import co.com.luis.salas.GestorUsuarios.repository.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation("Devuelve una lista de todos los usuarios")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista obtenida exitosamente"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<User>> obtenerTodosLosUsuarios() {
        try {
            List<User> usuarios = userRepository.findAll();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Devuelve un usuario por su cédula")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuario obtenido exitosamente"),
            @ApiResponse(code = 404, message = "Usuario no encontrado"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    @GetMapping("/{cedula}")
    public ResponseEntity<User> obtenerUsuarioPorCedula(@PathVariable int cedula) {
        try {
            Optional<User> usuario = userRepository.findByCedula(cedula);
            if (usuario.isPresent()) {
                return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Crea un nuevo usuario")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuario creado exitosamente"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody User usuario) {
        try {
            userRepository.save(usuario);
            return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Actualiza un usuario existente por su cédula")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuario actualizado exitosamente"),
            @ApiResponse(code = 404, message = "Usuario no encontrado"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    @PutMapping("/{cedula}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable int cedula, @RequestBody User detallesUsuario) {
        try {
            Optional<User> usuario = userRepository.findByCedula(cedula);
            if (usuario.isPresent()) {
                User usuarioActualizado = usuario.get();
                usuarioActualizado.setNombre(detallesUsuario.getNombre());
                usuarioActualizado.setEmail(detallesUsuario.getEmail());
                usuarioActualizado.setTelefono(detallesUsuario.getTelefono());
                userRepository.save(usuarioActualizado);
                return new ResponseEntity<>("Usuario actualizado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Elimina un usuario por su cédula")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuario eliminado exitosamente"),
            @ApiResponse(code = 404, message = "Usuario no encontrado"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int cedula) {
        try {
            Optional<User> usuario = userRepository.findByCedula(cedula);
            if (usuario.isPresent()) {
                userRepository.delete(usuario.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
