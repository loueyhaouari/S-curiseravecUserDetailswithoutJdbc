package com.example.demo.web;

import com.example.demo.entities.Patient;
import com.example.demo.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller @AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    //Le defaut de GetMapping used with delete c'est l'utilisateur peut supprimer les données depuis URL
    @GetMapping("/delete")
    public String delete(Long id,String keyword, int page){
patientRepository.deleteById(id);
//<!-- Il faut garder le mot clé pour rester sur la meme page -->

return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @GetMapping("/editPatient")
    public String editPatient(Model model,@RequestParam(name = "id") Long id){
        Patient patient=patientRepository.findById(id).get();
        model.addAttribute("patient",patient);
        return "editPatient";
    }
    //S'il ya une message d'erreur on le stocke dans une collection BindingResult

    @PostMapping("/savePatient")
     public String savePatient(@Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "formPatients";
        }
      patientRepository.save(patient);
        return "redirect:/index?keyword="+patient.getNom();
    }
@GetMapping("/index")
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int p
        ,@RequestParam(name = "size",defaultValue = "4") int s,@RequestParam(name = "keyword",defaultValue = "") String kw){
//    List<Patient>patientList=patientRepository.findAll();
    //Page<Patient> pagePatient=patientRepository.findAll(PageRequest.of(p,s));
    Page<Patient> pagePatient=patientRepository.findByNomContains(kw,PageRequest.of(p,s));
    //Stockkage de patientList dans ListPatients
    //getContent liste patient
    model.addAttribute("ListPatients",pagePatient.getContent());
    //pagePatient.getTotalPages() retourne le nombre de page de pagePatient
    model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
    model.addAttribute("currentPage",p);
    model.addAttribute("keyword",kw);
    return "patients";
}
@GetMapping("/")
    public String home(){
        return "redirect:/index";
}
}
