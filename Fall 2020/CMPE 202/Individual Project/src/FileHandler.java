public interface FileHandler {
    public void handleFile(String file, String fileOut);
    public void setSuccessor(FileHandler next);
}
