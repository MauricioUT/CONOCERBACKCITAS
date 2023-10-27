package org.conocer.citas.dao;

import org.conocer.citas.dto.CitasDto;
import org.conocer.citas.dto.EstatusUpdateDto;
import org.conocer.citas.model.ResumenCitas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class CitasDaoImpl extends JdbcDaoSupport implements CitasDao {

    @Qualifier("citas_Datasource")
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }


    @Override
    public void createCita(CitasDto request, String idMeet, String idCalendar) {

        String sql = "insert into k_citas (nombre, empresa, id_estado, id_ocupacion, id_medio, descripcion_solicitud,email, fecha, id_hora, id_meet, fecha_registro, no_telefono, id_calendar) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        getJdbcTemplate().update(sql, new Object[]{
                request.getNombre(), request.getEmpresa(), request.getEntidad(), request.getOcupacion(), request.getMedio(), request.getDescripcion(), request.getEmail(),
                sumarDiasAFecha(request.getFecha(),0), request.getHorario(), idMeet, new Date(), request.getTelefono(), idCalendar});// se le sumaba un día ya que el servidor tenía mal la zona horaria

    }

    @Override
    public boolean eventExist(Date fecha, String hora) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("la fecha si existe es :" + fecha);
        Date date2 = sumarDiasAFecha(fecha,1);
        String date = formatter.format(date2);
        System.out.println("la con formato mas un día es: " + date);
        String sql = "select count(h.id) from k_citas c " +
                "right join c_horarios h on c.id_hora = h.id and c.fecha = ?" +
                "where c.id_hora = ? ";
        Integer existe = getJdbcTemplate().queryForObject(sql, new Object[]{date, hora}, Integer.class);

        return existe == 1 ? false : true;
    }

    @Override
    public List<ResumenCitas> resumenCitas() {
        String sql = "SELECT c.id as id, c.nombre AS nombre, c.empresa AS empresa, e.entidades AS entidaes," +
                " o.ocupacion AS ocupacion, m.medio AS medio, c.descripcion_solicitud AS descripcion," +
                " c.email AS email, c.fecha AS fecha, h.horarios AS horario, c.id_meet  AS meet," +
                " c.no_telefono AS telefono, c.id_calendar AS idCalendario, v.descripcion as estatus, obs.observaciones as observaciones" +
                " FROM k_citas c" +
                " INNER JOIN c_horarios h ON h.id = c.id_hora" +
                " INNER JOIN c_medios_conocer m ON m.id = c.id_medio" +
                " INNER JOIN c_ocupacion_actual o ON o.id = c.id_ocupacion" +
                " INNER JOIN c_entidades e ON e.id = c.id_estado" +
                " INNER JOIN c_estatus_videollamadas v ON c.id_estatus_videollamada = v.id" +
                " LEFT JOIN k_citas_observaciones obs ON obs.id_citas =  c.id" +
                " ORDER BY fecha asc,horario ";

        List<Map<String, Object>> rows =  getJdbcTemplate().queryForList(sql, new Object[]{});
        List<ResumenCitas> result = new ArrayList<ResumenCitas>();
        for(Map<String, Object> row:rows){
            ResumenCitas citas = new ResumenCitas();
            citas.setId((Long) row.get(("id")));
            citas.setNombre((String)row.get("nombre"));
            citas.setEmpresa((String)row.get("empresa"));
            citas.setEstado((String)row.get("entidaes"));
            citas.setOcupacion((String)row.get("ocupacion"));
            citas.setMedio((String)row.get("medio"));
            citas.setDescripcion((String)row.get("descripcion"));
            citas.setEmail((String)row.get("email"));
            citas.setFecha((Date)row.get("fecha"));
            citas.setHora((String)row.get("horario"));
            citas.setMeet((String)row.get("meet"));
            citas.setTelefono((Long) row.get(("telefono")));
            citas.setIdCalendar((String) row.get("idCalendario"));
            citas.setEstatus((String) row.get("estatus"));
            citas.setObservaciones((String) row.get("observaciones"));

            result.add(citas);
        }

        return result;
    }

    @Override
    public void actualizarObs(EstatusUpdateDto req) {
        String sql = "update k_citas set id_estatus_videollamada = ? where id = ?";
        getJdbcTemplate().update(sql, new Object[]{req.getEstatus(), req.getId()});
    }

    @Override
    public void insertObs(EstatusUpdateDto req) {
        String sql = "INSERT INTO k_citas_observaciones (id_citas, observaciones) VALUES (?, ?)";
        getJdbcTemplate().update(sql, new Object[]{req.getId(), req.getObservaciones()});
    }


    public static Date sumarDiasAFecha(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }



}
