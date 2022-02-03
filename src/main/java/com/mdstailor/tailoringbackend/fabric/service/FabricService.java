package com.mdstailor.tailoringbackend.fabric.service;
import com.mdstailor.tailoringbackend.exceptions.fabricnotfound.FabricNotFoundException;
import com.mdstailor.tailoringbackend.fabric.entity.Fabric;
import com.mdstailor.tailoringbackend.fabric.repository.FabricRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;



@Transactional
@Service
public class FabricService {

    private final FabricRepository fabricRepository;
    @Autowired
    public FabricService(FabricRepository fabricRepository) {
        this.fabricRepository = fabricRepository;
    }

    public Fabric addFabric(Fabric fabric){
        return fabricRepository.save(fabric);
    }

    public List<Fabric> findAllFabric(){
        return fabricRepository.findAll();
    }

    public void deleteFabric(Long id){
        fabricRepository.deleteFabricById(id);
    }
    public Fabric findFabricById(Long id){
        return fabricRepository.findFabricById(id).orElseThrow(()->
                new FabricNotFoundException("Fabric by id"+ id + "was not found"));
    }


}
