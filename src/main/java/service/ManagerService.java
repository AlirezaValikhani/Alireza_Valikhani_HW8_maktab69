package service;

import model.human.Manager;
import repository.ManagerRepository;

import java.sql.Connection;
import java.util.List;

public class ManagerService implements BaseService<Manager> {

    private Connection connection;
    private ManagerRepository managerRepository;

    public ManagerService(Connection connection) {
        this.connection = connection;
        this.managerRepository = new ManagerRepository(this.connection);


    }

    @Override
    public Integer insert(Manager manager) {
        return managerRepository.insert(manager);
    }

    public Manager readByUsername(String username){
        return managerRepository.readByUsername(username);
    }

    @Override
    public Manager read(Manager manager) {
        return managerRepository.read(manager);
    }

    @Override
    public List<Manager> readAll() {
        return managerRepository.readAll();
    }

    @Override
    public Integer update(Manager manager) {
        return managerRepository.update(manager);
    }

    @Override
    public Integer delete(Manager manager) {
        return managerRepository.delete(manager);
    }
}
