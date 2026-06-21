package com.profession.suggest.database.services.dataanalys.vrtests;

import com.profession.suggest.database.entities.dataanalys.vrtests.VRTest;
import com.profession.suggest.database.entities.dataanalys.vrtests.VRTestAnswer;
import com.profession.suggest.database.repositories.dataanalys.vrtests.VRTestAnswerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class VRTestAnswerService {
    private final VRTestAnswerRepository repository;

    public VRTestAnswer create (VRTestAnswer vrTestAnswer) {
        return repository.save(vrTestAnswer);
    }
    public List<VRTestAnswer> createAll(Set<VRTestAnswer> answers) {
        return repository.saveAll(answers);
    }
    public void delete(VRTestAnswer answer) {
        repository.delete(answer);
    }

}
