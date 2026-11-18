package soa.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import soa.model.Tourist;
import soa.repository.TouristRepository;

@Service
public class TouristService {

    TouristRepository TR;

    public TouristService(TouristRepository TR)
    {
        this.TR = TR;
    }

    // Add Tourist
    public Object addTourist(Tourist T)
    {
        Map<String, Object> response = new HashMap<>();
        try
        {
            TR.save(T);

            response.put("status", "success");
            response.put("message", "New tourist has been added");
        }
        catch(Exception e)
        {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    // Update Tourist
    public Object updateTourist(Long id, Tourist T)
    {
        Map<String, Object> response = new HashMap<>();
        try
        {
            Tourist temp = TR.findById(id).get();

            temp.setName(T.getName());
            temp.setDestination(T.getDestination());
            temp.setContact(T.getContact());

            TR.save(temp);

            response.put("status", "success");
            response.put("message", "Tourist Updated Successfully");
        }
        catch(Exception e)
        {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }
}