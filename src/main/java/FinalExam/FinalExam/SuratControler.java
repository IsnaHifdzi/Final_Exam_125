/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalExam.FinalExam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author acer
 */
@RestController
@ResponseBody
public class SuratControler {
    
    //Isna Hifdzi Walidain_20200140125
    
    Surat dataa = new Surat();
    SuratJpaController controll = new SuratJpaController();
    
    @GetMapping (value = "/GET", produces = APPLICATION_JSON_VALUE)
    public  List<Surat> getData(){
        List<Surat> buffer = new ArrayList<>();
        try{
            buffer=controll.findSuratEntities();
        }
        catch(Exception Error){
            
        }return buffer;
        
    }
    @PostMapping(value = "/POST", consumes = APPLICATION_JSON_VALUE)
    public String sendData (HttpEntity<String>datasend) throws  JsonProcessingException
    {
        String feedback = "DO Nothing";
        ObjectMapper mapper = new ObjectMapper();
        dataa = mapper.readValue(datasend.getBody(),Surat.class);
        try{
            controll.create(dataa);
            feedback = dataa.getJudul()+" Saved";
        }catch(Exception Error){
            feedback = Error.getMessage();
            
        }return feedback;
    }
     @PutMapping(value = "/PUT", consumes = APPLICATION_JSON_VALUE)
    public String updateData (HttpEntity<String> datasend) throws JsonProcessingException
    {
        String feedback = "Do Nothing";
        ObjectMapper mapper = new ObjectMapper();
        dataa = mapper.readValue(datasend.getBody(), Surat.class);
                try
                {
                    controll.edit(dataa);
                    feedback = dataa.getJudul()+ " Edited";
                }
                catch (Exception Error)
                        {
                            feedback = Error.getMessage();
                        }
                return feedback;
    }
    @DeleteMapping(value = "/DELETE", consumes = APPLICATION_JSON_VALUE)
    public String deleteData (HttpEntity<String> datasend) throws JsonProcessingException
    {
        String feedback = "Do Nothing";
        ObjectMapper mapper = new ObjectMapper();
        dataa = mapper.readValue(datasend.getBody(), Surat.class);
                try
                {
                    controll.destroy(dataa.getId());
                    feedback = dataa.getJudul()+ " Deleted";
                }
                catch (Exception Error)
                        {
                            feedback = Error.getMessage();
                        }
                return feedback;
    }    
}
