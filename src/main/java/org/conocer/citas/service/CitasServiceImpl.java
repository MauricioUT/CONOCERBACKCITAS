package org.conocer.citas.service;

import org.conocer.citas.calendar.CalendarQuickstart;
import org.conocer.citas.dao.CitasDao;
import org.conocer.citas.dto.CitasDto;
import org.conocer.citas.dto.EstatusUpdateDto;
import org.conocer.citas.dto.Mail;
import org.conocer.citas.dto.Response;
import org.conocer.citas.model.ResumenCitas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class CitasServiceImpl implements CitasService {

    @Autowired
    CitasDao citasDao;

    @Autowired
    MailService mailService;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void createEvent(CitasDto request, String idMeet, String idCalendar) {
        citasDao.createCita(request, idMeet, idCalendar);
    }

    @Override
    public boolean eventExists(Date fecha, String hora) {
        return citasDao.eventExist(fecha, hora);
    }

    @Override
    public List<ResumenCitas> getResumenCitas() {
        return citasDao.resumenCitas();
    }

    @Override
    public Response updateStatus(EstatusUpdateDto req) {
        Response response = null;
        try {
            citasDao.actualizarObs(req);
            citasDao.insertObs(req);
            Mail mail = new Mail();
            if (req.getEstatus() == 3) { // se Envia el correo agregando el motivo de la baja
                mail.setMailFrom(emailFrom);
                mail.setMailTo(req.getEmail());
                mail.setMailSubject("Videollamada cancelada");
                mail.setMailContent(req.getObservaciones());
                //mailService = (MailService) ApplicationContext.getBean("mailService");
                mailService.sendEmail(mail);
                CalendarQuickstart.delete(req.getIdCalendar());
            }
            response = new Response(1, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(-1, "algo salio mal");
        }
        return response;
    }

    @Override
    public void changeSchedule(CitasDto request) throws ParseException {
        System.out.println("Hora inicio ->>>>>> Entrada " + request.getFechaInicio());
        System.out.println("Hora fin ->>>>>> Entrada " + request.getFechaFin());
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm Z");
        final TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");
        String fechaInicio = request.getFechaInicio().split("T")[0];
        String horaInicio = request.getFechaInicio().split("T")[1];
        String fechaFin = request.getFechaFin().split("T")[0];
        String horaFin = request.getFechaFin().split("T")[1];

        Date feIn = getSomeDate(fechaInicio + " " + horaInicio.split("-")[0], tz);
        Date feFn = getSomeDate(fechaFin + " " + horaFin.split("-")[0], tz);
        Date currentDate = Calendar.getInstance(tz).getTime();
        Date oct = getSomeDate((currentDate.getYear() + 1900) + "-10-31 24:00", tz);
        //google clanedar le aumenta una hora en el horario de verano el en else se compensa esa hora
        if (currentDate.before(oct) && feIn.after(oct)) {// sigue mandando una hora menos después del cambio de horario, por eso se descomenta, comentar cuando se haga el cambio de horario
            System.out.println("hago el cambio de horaro");
            System.out.println("horario inicio " + feIn);
            System.out.println("horario fin " + feFn);
            feIn = addHours(feIn, 3);
            feFn = addHours(feFn, 3);
            request.setFechaInicio(sdf.format(feIn).split(" -")[0].split(" ")[0] + "T" + sdf.format(feIn).split(" -")[0].split(" ")[1] + ":00-05:00");
            request.setFechaFin(sdf.format(feFn).split(" -")[0].split(" ")[0] + "T" + sdf.format(feFn).split(" -")[0].split(" ")[1] + ":00-05:00");
            System.out.println("horario inicio " + feIn);
            System.out.println("horario Fin " + feFn);
        }else{ //se agrega una hora en el horario de verano
            System.out.println("hago el cambio de horaro");
            System.out.println("horario inicio " + feIn);
            System.out.println("horario fin " + feFn);
            feIn = addHours(feIn, 1);
            feFn = addHours(feFn, 1);
            request.setFechaInicio(sdf.format(feIn).split(" -")[0].split(" ")[0] + "T" + sdf.format(feIn).split(" -")[0].split(" ")[1] + ":00-05:00");
            request.setFechaFin(sdf.format(feFn).split(" -")[0].split(" ")[0] + "T" + sdf.format(feFn).split(" -")[0].split(" ")[1] + ":00-05:00");
            System.out.println("horario inicio " + feIn);
            System.out.println("horario Fin " + feFn);
        }
        System.out.println("Fecha actual ->>>>>> Salida " + currentDate);
        System.out.println("Hora inicio  ->>>>>> Salida " + request.getFechaInicio());
        System.out.println("Hora inicio  ->>>>>> Salida " + request.getFechaFin());
    }

    public static Date getSomeDate(final String str, final TimeZone tz)
            throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        sdf.setTimeZone(tz);
        return sdf.parse(str);
    }

    public Date addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }


}
