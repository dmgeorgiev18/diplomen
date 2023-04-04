package com.project.motohotel.repository;

import com.project.motohotel.entity.Model;
import java.util.List;
public interface ModelService {

    List<Model>findAllMogels();

    Model findById(String id);
}
