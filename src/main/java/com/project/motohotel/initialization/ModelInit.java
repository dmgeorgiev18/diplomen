package com.project.motohotel.initialization;

import com.project.motohotel.entity.Manufacturer;
import com.project.motohotel.entity.Model;
import com.project.motohotel.repository.ManufacturerRepository;
import com.project.motohotel.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class ModelInit implements CommandLineRunner {
    private ModelRepository repository;
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    public ModelInit(ModelRepository repository, ManufacturerRepository manufacturerRepository) {
        this.repository = repository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count()==0){
            Manufacturer ktm = manufacturerRepository.findByName("KTM");
            Manufacturer yamaha = manufacturerRepository.findByName("Yamaha");
            Manufacturer kawazaki = manufacturerRepository.findByName("Kawasaki");
            Manufacturer bmw = manufacturerRepository.findByName("BMW");

            repository.save(new Model("105",ktm));
            repository.save(new Model("Prestige",ktm));
            repository.save( new Model("Enduro",ktm));
            repository.save( new Model("Supermoto",ktm));
            repository.save( new Model("Adventure",ktm));
            repository.save( new Model("125",ktm));

            repository.save(new Model("BT",yamaha));
            repository.save(new Model("Xt",yamaha));
            repository.save(new Model("Big Bear",yamaha));
            repository.save(new Model("Black Max",yamaha));
            repository.save(new Model("Bolt",yamaha));
            repository.save(new Model("Booster",yamaha));
            repository.save(new Model("Drag Star",yamaha));

            repository.save(new Model("100",kawazaki));
            repository.save(new Model("Brute Force",kawazaki));
            repository.save(new Model("G3",kawazaki));
            repository.save(new Model("G5",kawazaki));
            repository.save(new Model("125",kawazaki));
            repository.save(new Model("1000",kawazaki));


            repository.save(new Model("C",bmw));
            repository.save(new Model("F",bmw));
            repository.save(new Model("G",bmw));
            repository.save(new Model("K",bmw));
            repository.save(new Model("HP",bmw));



        }
    }
}
