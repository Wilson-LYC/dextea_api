package com.dextea.util;

import cn.hutool.core.util.IdUtil;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class AudioUntil {
    /**
     * 生成音频
     * @param code 取餐码
     * @return 返回base64
     * @throws UnsupportedAudioFileException 音频类型异常
     * @throws IOException IO异常
     */
    public static String createAudio(String code) throws UnsupportedAudioFileException, IOException {
        if (code.length()!=4){
            return "error";
        }
        String num1 = code.substring(0,1)+".wav";
        String num2 = code.substring(1,2)+".wav";
        String num3 = code.substring(2,3)+".wav";
        String num4 = code.substring(3,4)+".wav";
        String[] wavFiles = {"welcome.wav","call.wav",num1, num2,num3,num4,"do.wav"}; // 指定要拼接的 WAV 文件列表
        String outputFilePath = IdUtil.fastSimpleUUID()+"output.wav"; // 指定输出文件路径
        concatenateWAVFiles(wavFiles, outputFilePath);
        String base64String = null;
        try {
            base64String = convertToBase64(outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //删除文件
        File file = new File(outputFilePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        return base64String;
    }

    /**
     * 拼接wav音频
     * @param wavFiles wav源文件
     * @param outputFilePath 输出路径
     * @throws IOException IO异常
     * @throws UnsupportedAudioFileException 文件类型异常
     */
    public static void concatenateWAVFiles(String[] wavFiles, String outputFilePath) throws IOException, UnsupportedAudioFileException {
        AudioFormat audioFormat = null;
        AudioInputStream audioInputStream = null;
        AudioInputStream appendedFilesInputStream = null;

        for (String wavFile : wavFiles) {
            wavFile = "wav/" + wavFile;
            File file = new File(wavFile);
            AudioInputStream currentInputStream = AudioSystem.getAudioInputStream(file);

            if (audioFormat == null) {
                audioFormat = currentInputStream.getFormat();
            }

            if (appendedFilesInputStream == null) {
                appendedFilesInputStream = new AudioInputStream(currentInputStream, audioFormat, currentInputStream.getFrameLength());
            } else {
                appendedFilesInputStream = new AudioInputStream(new SequenceInputStream(appendedFilesInputStream, currentInputStream), audioFormat, appendedFilesInputStream.getFrameLength() + currentInputStream.getFrameLength());
            }
        }

        if (appendedFilesInputStream != null) {
            AudioSystem.write(appendedFilesInputStream, AudioFileFormat.Type.WAVE, new File(outputFilePath));
        }

        if (audioInputStream != null) {
            audioInputStream.close();
        }
    }

    /**
     * 音频转为base64
     * @param filePath 文件路径
     * @return base64编码
     * @throws IOException IO异常
     */
    public static String convertToBase64(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] fileData = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(fileData);
    }
}