package com.lca.analyzerlcaspring.service;

import com.lca.analyzerlcaspring.entity.AssignmentQuestion;
import com.lca.analyzerlcaspring.entity.StudentAssignment;
import com.lca.analyzerlcaspring.repository.AssignmentQuestionRepository;
import com.lca.analyzerlcaspring.repository.StudentAssignmentRepository;
import com.lca.core.CustomAnalyzer;
import com.lca.core.dto.LCAGenerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spoon.reflect.CtModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnalyzerServiceImpl implements AnalyzerService {
    Logger logger = LoggerFactory.getLogger(AnalyzerServiceImpl.class);

    @Autowired
    private AssignmentQuestionRepository assignmentQuestionRepository;

    @Autowired
    private StudentAssignmentRepository studentAssignmentRepository;

    @Autowired
    private List<CustomAnalyzer> customAnalyzerList;

    @Override
    public void generateQuestion(CtModel ctModel, String path) throws IOException {
//        for (CustomAnalyzer customAnalyzer : customAnalyzerList) {
//            logger.info("Receiving analyzer wrapper dto: " + customAnalyzer.getAnalyzerName());
//            try {
//                List<LCAGenerated> LCAGeneratedList = customAnalyzer.analyze(ctModel, studentAssignmentId);
//                for (LCAGenerated qlc : LCAGeneratedList) {
//                    AssignmentQuestion question2Save = new AssignmentQuestion();
//                    question2Save.setGeneratedText(qlc.getGeneratedText());
//                    question2Save.setHelpText(qlc.getHelpText());
//                    question2Save.setLevel(String.valueOf(qlc.getLevel()));
//                    question2Save.setScope(String.valueOf(qlc.getScope()));
//                    question2Save.setStudentAssignmentId(studentAssignmentId);
//                    assignmentQuestionRepository.save(question2Save);
//                }
//            } catch (Exception exception) {
//                logger.info(String.valueOf(exception));
//            }
//        }
        for (CustomAnalyzer customAnalyzer : customAnalyzerList) {
            List<AssignmentQuestion> listQuestion = new ArrayList<>();
            logger.info("Receiving analyzer wrapper dto: " + customAnalyzer.getAnalyzerName());
            try {
                List<LCAGenerated> LCAGeneratedList = customAnalyzer.analyze(ctModel, 1);
                for (LCAGenerated qlc : LCAGeneratedList) {
                    AssignmentQuestion question2Save = new AssignmentQuestion();
                    question2Save.setGeneratedText(qlc.getGeneratedText());
                    question2Save.setHelpText(qlc.getHelpText());
                    question2Save.setLevel(String.valueOf(qlc.getLevel()));
                    question2Save.setScope(String.valueOf(qlc.getScope()));
                    question2Save.setStudentAssignmentId(1);
                    listQuestion.add(question2Save);
//                    assignmentQuestionRepository.save(question2Save);
                }
            } catch (Exception exception) {
                logger.info(String.valueOf(exception));
            }
            File file = new File(path + "/artifact/Questions.txt");
            FileWriter fileWriter = new FileWriter(file);
            for (AssignmentQuestion question : listQuestion) {
                System.out.println(question.getHelpText());
                fileWriter.write("Question: " + question.getGeneratedText() + "\n");
            }
            fileWriter.close();
        }
    }
}
