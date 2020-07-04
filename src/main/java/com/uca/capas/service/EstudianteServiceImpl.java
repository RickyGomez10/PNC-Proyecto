package com.uca.capas.service;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.repositories.EstudianteRepo;
import com.uca.capas.repositories.MateriaXEstudianteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService{

    @Autowired
    EstudianteRepo estudianteRepo;

    @Autowired
    MateriaXEstudianteRepo mxeRepo;

    @Autowired
    private CentroEdService centroService;

    @Override
    public List<Estudiante> findAll() throws DataAccessException {
        return estudianteRepo.findAll();
    }

    @Override
    public Estudiante findOne(String carne) throws DataAccessException {
        return estudianteRepo.findEstudianteByCarne(carne);
    }

    @Override
    public Estudiante findById(Integer id) throws DataAccessException {
        return estudianteRepo.getOne(id);
    }

    @Override
    public void save(Estudiante estudiante) throws DataAccessException {
        estudiante.setCentroEd(centroService.findOne(estudiante.getcCentroEd()));
        estudianteRepo.save(estudiante);
    }

    @Override
    public void update(Estudiante estudiante) throws DataAccessException {
        estudianteRepo.save(estudiante);
    }

    @Override
    public List<Estudiante> filtrarPorNombre(String val) {
        return estudianteRepo.findEstudiantesByNombreContains(val);
    }

    @Override
    public List<Estudiante> filtrarPorApellido(String val) {
        return estudianteRepo.findEstudiantesByApellidoContains(val);
    }

    @Override
    public Integer getMateriasAprobadas(Integer id) throws DataAccessException {
        return mxeRepo.getMateriasAprobadas(id);
    }

    @Override
    public Integer getMateriasReprobadas(Integer id) throws DataAccessException {
        return mxeRepo.getMateriasReprobadas(id);
    }

    @Override
    public Float getPromedio(Integer id) throws DataAccessException {
        return mxeRepo.getPromedio(id);
    }
}
