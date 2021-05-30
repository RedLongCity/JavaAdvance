package course.advance.dao;

import course.advance.dto.Pudge;
import course.advance.exception.DataAccessException;
import course.advance.exception.WrongParamException;

import java.io.*;

public class PudgeEnvironmentRepository implements PudgeRepository {

    private final String pathToArchiveFile;

    public PudgeEnvironmentRepository(String pathToArchiveFile) {
        this.pathToArchiveFile = pathToArchiveFile;
    }

    @Override
    public Pudge extract() throws DataAccessException, WrongParamException {
        File file = new File(pathToArchiveFile);
        if (!file.exists())
            return null;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(pathToArchiveFile))) {
            int lvl = dis.readInt();
            String name = dis.readUTF();
            return new Pudge(name, lvl, null);
        } catch (IOException e) {
            throw new DataAccessException("[PUDGE EXTRACTING EX]", e);
        }
    }

    @Override
    public Pudge save(Pudge pudge) throws DataAccessException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(pathToArchiveFile))) {
            dos.writeInt(pudge.getLvl());
            dos.writeUTF(pudge.getName());
        } catch (IOException e) {
            throw new DataAccessException("[PUDGE SAVING EX]", e);
        }
        return pudge;
    }
}
