package org.conocer.citas.dao;

import org.conocer.citas.dto.CitasDto;
import org.conocer.citas.dto.EstatusUpdateDto;
import org.conocer.citas.model.ResumenCitas;

import java.util.Date;
import java.util.List;

public interface CitasDao {

    void createCita(CitasDto request, String idMeet, String idCalendar);

    boolean eventExist(Date fecha, String hora);

    List<ResumenCitas> resumenCitas();

    void actualizarObs(EstatusUpdateDto req);

    void insertObs(EstatusUpdateDto req);

}
