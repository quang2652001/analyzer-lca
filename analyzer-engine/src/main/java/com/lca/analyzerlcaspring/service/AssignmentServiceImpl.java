package com.lca.analyzerlcaspring.service;

import com.lca.analyzerlcaspring.entity.AssignmentStatus;
import com.lca.analyzerlcaspring.entity.StudentAssignment;
import com.lca.analyzerlcaspring.repository.StudentAssignmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spoon.reflect.CtModel;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    Logger LOGGER = LoggerFactory.getLogger(AssignmentServiceImpl.class);

    @Autowired
    private StudentAssignmentRepository studentAssignmentRepository;

    @Autowired
    private CloneService repositoryService;

    @Autowired
    private SpoonService spoonService;

    @Autowired
    private AnalyzerService analyzerService;


    @Override
    public void analyzeAssignment(String path) {
//        LOGGER.info("Start analyzing...");
//        LOGGER.info("Getting assignment...");
//        List<StudentAssignment> submittedAssignment = studentAssignmentRepository
//                .findByStatus(AssignmentStatus.SUBMITTED.name());
//        LOGGER.info("Got {} assignment.", submittedAssignment.size());
//        submittedAssignment.forEach(candidate -> {
//            int id = candidate.getId();
//            String url = candidate.getUrl();
//
//            try {
//                repositoryService.cloneRepo(id, url);
//                LOGGER.info("Start analyzing submitted assignment: " + id);
//                CtModel ctModel = spoonService.buildSpoonModel(id);
//                analyzerService.generateQuestion(ctModel, id);
//                LOGGER.info("Finish analyzing submitted assignment: " + id);
//                repositoryService.cleanRepo(id);
//            } catch (Exception exception) {
//                LOGGER.error(exception.getMessage());
//            }
//
//            candidate.setStatus(AssignmentStatus.GENERATED.name());
//            studentAssignmentRepository.save(candidate);
//        });


        LOGGER.info("Start analyzing...");
        LOGGER.info("Getting assignment...");
        int id = 1;
        try {
            LOGGER.info("Start analyzing assignment: ");
            CtModel ctModel = spoonService.buildSpoonModel(path);
            analyzerService.generateQuestion(ctModel, path);
            LOGGER.info("Finish analyzing submitted assignment: " + id);
//            repositoryService.cleanRepo(id);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
    }
}
