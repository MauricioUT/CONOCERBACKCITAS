package org.conocer.citas.controller;

import com.google.api.services.calendar.model.Event;
import org.conocer.citas.calendar.CalendarQuickstart;
import org.conocer.citas.dto.CatalogosResponseDto;
import org.conocer.citas.dto.CitasDto;
import org.conocer.citas.dto.EstatusUpdateDto;
import org.conocer.citas.dto.Response;
import org.conocer.citas.model.Catalogo;
import org.conocer.citas.model.ResumenCitas;
import org.conocer.citas.service.CatalogoService;
import org.conocer.citas.service.CitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value = "/evento", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
public class EventoController {

    @Autowired
    CatalogoService catalogoService;

    @Autowired
    CitasService citasService;

    @PostMapping(value = "/crearEvento", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response citas(@RequestBody CitasDto request) {
        System.out.println("esta fecha es: " + request.getFecha());
        Response response = null;
        boolean existe = citasService.eventExists(request.getFecha(), request.getHorario());
        if (existe) {
            try {
                citasService.changeSchedule(request);
                Event event = CalendarQuickstart.create(request);
                citasService.createEvent(request, event.getHangoutLink(), event.getId());
                response = new Response(1, "ok");
            } catch (IOException e) {
              e.printStackTrace();
             response = new Response(-1, "algo salio mal");
            } catch (Exception e) {
                e.printStackTrace();
                response = new Response(-1, "algo salio mal");
            }
        }
        return response;
    }

    @GetMapping(value = "/getCatalogos")
    public CatalogosResponseDto catalogos() {
        CatalogosResponseDto catalogosResponseDto = null;
        List<Catalogo> entidades = catalogoService.getCatalogo("c_entidades", "entidades");
        List<Catalogo> medios = catalogoService.getCatalogo("c_medios_conocer", "medio");
        List<Catalogo> ocupacion = catalogoService.getCatalogo("c_ocupacion_actual", "ocupacion");
        catalogosResponseDto = new CatalogosResponseDto(new ArrayList<>(), entidades, medios, ocupacion);
        return catalogosResponseDto;
    }

    @PostMapping(value = "/getHorariosByFecha", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Catalogo> horarios(@RequestBody Date request) {
        List<Catalogo> horario = catalogoService.getHorario(request);
        return horario;
    }

    @GetMapping(value = "/resumenCitas")
    public List<ResumenCitas>  resumenCitas(){
        return citasService.getResumenCitas();
    }

    @PostMapping(value = "/statusUpdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response  statusUpdate(@RequestBody EstatusUpdateDto request){
        Response response = citasService.updateStatus(request);
        return response;
    }
}