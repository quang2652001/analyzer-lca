package com.lca.analyzerlcaspring.service;

import spoon.reflect.CtModel;

import java.io.IOException;

public interface AnalyzerService {
    void generateQuestion(CtModel ctModel, String path) throws IOException;
}
