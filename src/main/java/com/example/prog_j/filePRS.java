package com.example.prog_j;
import java.util.Objects;

public class filePRS {
    private static final String KEY = "1234password4321";

    public static void ReadInputFile(FileSettings InputFileSettings) throws Exception {
        if (InputFileSettings.check_file_zip && InputFileSettings.check_file_encrypted) {
            if (Objects.equals(InputFileSettings.file_type, ".txt")) {
                InputFileSettings.byte_info = archManager.readBytesFromFileInArchive(InputFileSettings.archive_name, InputFileSettings.file_name);
                InputFileSettings.byte_info = encrypt.DecryptBytes(InputFileSettings.key, InputFileSettings.byte_info);
                InputFileSettings.txt_info = fileOP.BytesToString(InputFileSettings.byte_info);
            } else if (Objects.equals(InputFileSettings.file_type, ".json")) {
                InputFileSettings.byte_info = archManager.readBytesFromFileInArchive(InputFileSettings.archive_name, InputFileSettings.file_name);
                InputFileSettings.byte_info = encrypt.DecryptBytes(InputFileSettings.key, InputFileSettings.byte_info);
                InputFileSettings.txt_info = fileOP.BytesToString(InputFileSettings.byte_info);

                JsonParser parser = new JsonParser();

                InputFileSettings.txt_info = parser.ParseStringByParser(InputFileSettings.txt_info);
                InputFileSettings.byte_info = fileOP.StringToBytes(InputFileSettings.txt_info);
            } else if (Objects.equals(InputFileSettings.file_type, ".xml")) {
                InputFileSettings.byte_info = archManager.readBytesFromFileInArchive(InputFileSettings.archive_name, InputFileSettings.file_name);
                InputFileSettings.byte_info = encrypt.DecryptBytes(InputFileSettings.key, InputFileSettings.byte_info);
                InputFileSettings.txt_info = fileOP.BytesToString(InputFileSettings.byte_info);

                XmlParser parser = new XmlParser();

                InputFileSettings.txt_info = parser.ParseStringByDOM(InputFileSettings.txt_info);
                InputFileSettings.byte_info = fileOP.StringToBytes(InputFileSettings.txt_info);
            }
        } else if (InputFileSettings.check_file_encrypted) {
            if (Objects.equals(InputFileSettings.file_type, ".txt")) {
                InputFileSettings.txt_info = encrypt.DecryptFileToString(InputFileSettings.key, InputFileSettings.file_name);
                InputFileSettings.byte_info = encrypt.DecryptFileToBytes(InputFileSettings.key, InputFileSettings.file_name);
            } else if (Objects.equals(InputFileSettings.file_type, ".json")) {
                InputFileSettings.txt_info = encrypt.DecryptFileToString(InputFileSettings.key, InputFileSettings.file_name);

                JsonParser parser = new JsonParser();
                InputFileSettings.txt_info = parser.ParseStringByParser(InputFileSettings.txt_info);
                InputFileSettings.byte_info = fileOP.StringToBytes(InputFileSettings.txt_info);
            } else if (Objects.equals(InputFileSettings.file_type, ".xml")) {
                InputFileSettings.txt_info = encrypt.DecryptFileToString(InputFileSettings.key, InputFileSettings.file_name);

                XmlParser parser = new XmlParser();
                InputFileSettings.txt_info = parser.ParseStringByDOM(InputFileSettings.txt_info);
                InputFileSettings.byte_info = fileOP.StringToBytes(InputFileSettings.txt_info);
            }
        } else if (InputFileSettings.check_file_zip) {
            if (Objects.equals(InputFileSettings.file_type, ".txt")) {
                InputFileSettings.txt_info = archManager.readStringFromFileInArchive(InputFileSettings.archive_name, InputFileSettings.file_name);
                InputFileSettings.byte_info = archManager.readBytesFromFileInArchive(InputFileSettings.archive_name, InputFileSettings.file_name);
            } else if (Objects.equals(InputFileSettings.file_type, ".json")) {
                InputFileSettings.txt_info = archManager.readStringFromFileInArchive(InputFileSettings.archive_name, InputFileSettings.file_name);

                JsonParser parser = new JsonParser();
                InputFileSettings.txt_info = parser.ParseStringByParser(InputFileSettings.txt_info);
                InputFileSettings.byte_info = fileOP.StringToBytes(InputFileSettings.txt_info);
            } else if (Objects.equals(InputFileSettings.file_type, ".xml")) {
                InputFileSettings.txt_info = archManager.readStringFromFileInArchive(InputFileSettings.archive_name, InputFileSettings.file_name);

                XmlParser parser = new XmlParser();
                InputFileSettings.txt_info = parser.ParseStringByDOM(InputFileSettings.txt_info);
                InputFileSettings.byte_info = fileOP.StringToBytes(InputFileSettings.txt_info);
            }
        } else {
            if (Objects.equals(InputFileSettings.file_type, ".txt")) {
                InputFileSettings.byte_info = fileOP.readBytesFromFile(InputFileSettings.file_name);
                InputFileSettings.txt_info = fileOP.readStringFromFile(InputFileSettings.file_name);
            } else if (Objects.equals(InputFileSettings.file_type, ".json")) {
                JsonParser parser = new JsonParser();
                InputFileSettings.txt_info = parser.ParseFileByParser(InputFileSettings.file_name);
                InputFileSettings.byte_info = fileOP.StringToBytes(InputFileSettings.txt_info);
            } else if (Objects.equals(InputFileSettings.file_type, ".xml")) {
                XmlParser parser = new XmlParser();
                InputFileSettings.txt_info = parser.ParseFileByDOM(InputFileSettings.file_name);
                InputFileSettings.byte_info = fileOP.StringToBytes(InputFileSettings.txt_info);
            }
        }
    }

    public static void WriteOutputFile(FileSettings OutputFileSettings) throws Exception {
        if (OutputFileSettings.check_file_zip && OutputFileSettings.check_file_encrypted) {
            archManager.writeBytesToArchive(encrypt.EncryptBytes(OutputFileSettings.key, OutputFileSettings.byte_info), OutputFileSettings.archive_name, OutputFileSettings.file_name);
            if (Objects.equals(OutputFileSettings.file_type, ".txt")) {
                archManager.writeBytesToArchive(encrypt.EncryptBytes(OutputFileSettings.key, OutputFileSettings.byte_info), OutputFileSettings.archive_name, OutputFileSettings.file_name);
            } else if (Objects.equals(OutputFileSettings.file_type, ".json")) {
                JsonParser parser = new JsonParser();

                OutputFileSettings.byte_info = fileOP.StringToBytes(parser.makeJson(OutputFileSettings.txt_info));
                archManager.writeBytesToArchive(encrypt.EncryptBytes(OutputFileSettings.key, OutputFileSettings.byte_info), OutputFileSettings.archive_name, OutputFileSettings.file_name);
            } else if (Objects.equals(OutputFileSettings.file_type, ".xml")) {
                XmlParser parser = new XmlParser();

                OutputFileSettings.byte_info = fileOP.StringToBytes(parser.makeXml(OutputFileSettings.txt_info));
                archManager.writeBytesToArchive(encrypt.EncryptBytes(OutputFileSettings.key, OutputFileSettings.byte_info), OutputFileSettings.archive_name, OutputFileSettings.file_name);
            }
        } else if (OutputFileSettings.check_file_encrypted) {
            if (Objects.equals(OutputFileSettings.file_type, ".txt")) {
                fileOP.WriteBytesToFile(encrypt.EncryptBytes(OutputFileSettings.key, OutputFileSettings.byte_info), OutputFileSettings.file_name);
            } else if (Objects.equals(OutputFileSettings.file_type, ".json")) {
                JsonParser parser = new JsonParser();

                OutputFileSettings.byte_info = fileOP.StringToBytes(parser.makeJson(OutputFileSettings.txt_info));
                fileOP.WriteBytesToFile(encrypt.EncryptBytes(OutputFileSettings.key, OutputFileSettings.byte_info), OutputFileSettings.file_name);
            } else if (Objects.equals(OutputFileSettings.file_type, ".xml")) {
                XmlParser parser = new XmlParser();

                OutputFileSettings.byte_info = fileOP.StringToBytes(parser.makeXml(OutputFileSettings.txt_info));
                fileOP.WriteBytesToFile(encrypt.EncryptBytes(OutputFileSettings.key, OutputFileSettings.byte_info), OutputFileSettings.file_name);
            }
        } else if (OutputFileSettings.check_file_zip) {
            if (Objects.equals(OutputFileSettings.file_type, ".txt")) {
                archManager.writeStringToArchive(OutputFileSettings.txt_info, OutputFileSettings.archive_name, OutputFileSettings.file_name);
            } else if (Objects.equals(OutputFileSettings.file_type, ".json")) {
                JsonParser parser = new JsonParser();
                archManager.writeStringToArchive(parser.makeJson(OutputFileSettings.txt_info), OutputFileSettings.archive_name, OutputFileSettings.file_name);
            } else if (Objects.equals(OutputFileSettings.file_type, ".xml")) {
                XmlParser parser = new XmlParser();
                archManager.writeStringToArchive(parser.makeXml(OutputFileSettings.txt_info), OutputFileSettings.archive_name, OutputFileSettings.file_name);
            }
        } else {
            if (Objects.equals(OutputFileSettings.file_type, ".txt")) {
                fileOP.WriteBytesToFile(OutputFileSettings.byte_info, OutputFileSettings.file_name);
            } else if (Objects.equals(OutputFileSettings.file_type, ".json")) {
                JsonParser parser = new JsonParser();
                fileOP.WriteStringToFile(parser.makeJson(OutputFileSettings.txt_info), OutputFileSettings.file_name);
            } else if (Objects.equals(OutputFileSettings.file_type, ".xml")) {
                XmlParser parser = new XmlParser();
                fileOP.WriteStringToFile(parser.makeXml(OutputFileSettings.txt_info), OutputFileSettings.file_name);
            }
        }
    }

    public static void ParseMathematicalExpressions(FileSettings InputFileSettings, FileSettings OutputFileSettings, String ParseType) throws Exception {
        MatchParserDirector matchParserDirector = new MatchParserDirector();

        switch (ParseType) {
            case "Parse by stack" -> matchParserDirector.SetBuilder(new MatchParserByStack());
            case "Parse by lib" -> matchParserDirector.SetBuilder(new MatchParserByLib());
            case "Parse by regular" -> matchParserDirector.SetBuilder(new MatchParserByRegular());
            default -> throw new IllegalStateException("Unexpected value: " + ParseType);
        }

        matchParserDirector.GetAnswer(InputFileSettings, OutputFileSettings);
    }

}
