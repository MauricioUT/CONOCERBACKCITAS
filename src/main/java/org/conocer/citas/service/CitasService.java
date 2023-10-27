package org.conocer.citas.service;

import org.conocer.citas.dto.CitasDto;
import org.conocer.citas.dto.EstatusUpdateDto;
import org.conocer.citas.dto.Response;
import org.conocer.citas.model.ResumenCitas;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface CitasService {

    void createEvent(CitasDto request, String idMeet, String idCalendar);

    boolean eventExists(Date fecha, String hora);

    List<ResumenCitas> getResumenCitas();

    Response updateStatus(EstatusUpdateDto req);

    void changeSchedule(CitasDto request) throws ParseException;
}
