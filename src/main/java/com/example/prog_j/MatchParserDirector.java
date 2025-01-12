package com.example.prog_j;

import com.example.prog_j.MatchParserBuilder;

public class MatchParserDirector {
    MatchParserBuilder matchParserBuilder;

    public MatchParserBuilder GetBuilder() {
        return this.matchParserBuilder;
    }

    public void SetBuilder(MatchParserBuilder matchParserBuilder) {
        this.matchParserBuilder = matchParserBuilder;
    }

    void GetAnswer(FileSettings InputFileSettings, FileSettings OutputFileSettings) throws Exception {
        matchParserBuilder.ParseTxt(InputFileSettings, OutputFileSettings);
    }
}
