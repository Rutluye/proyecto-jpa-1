package src.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonaController {

    private final PersonaRepository personaRepository;

    // Constructor y otras operaciones...

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @PostMapping("/registrar")
    public String registrarPersona(@RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("dni") String dni,
            @RequestParam("edad") int edad) {
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(nombre);
        nuevaPersona.setApellido(apellido);
        nuevaPersona.setDni(dni);
        nuevaPersona.setEdad(edad);
        personaRepository.save(nuevaPersona);
        return "redirect:/registro";
    }

    // Constructor y otras operaciones...

    @GetMapping("/lista")
    public String listarPersonas(Model model) {
        List<Persona> personas = personaRepository.findAll();
        model.addAttribute("personas", personas);
        return "lista"; // Retorna el nombre del archivo HTML (lista.html) sin la extensión
    }

    @GetMapping("/eliminacion")
    public String mostrarFormularioEliminacion(Model model) {
        List<Persona> personas = personaRepository.findAll();
        model.addAttribute("personas", personas);
        return "eliminacion";
    }

    @PostMapping("/eliminar")
    public String eliminarPersona(@RequestParam("personaId") Long personaId) {
        personaRepository.deleteById(personaId);
        return "redirect:/eliminacion";
    }

}
