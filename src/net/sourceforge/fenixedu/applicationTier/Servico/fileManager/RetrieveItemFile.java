package net.sourceforge.fenixedu.applicationTier.Servico.fileManager;

import net.sourceforge.fenixedu.fileSuport.FileSuportObject;
import net.sourceforge.fenixedu.persistenceTier.fileSupport.JdbcMysqlFileSupport;

import org.apache.slide.common.SlideException;

import pt.utl.ist.berserk.logic.serviceManager.IService;

public class RetrieveItemFile implements IService {

    public static FileSuportObject run(final String slidename, final String fileName) throws SlideException {
        return JdbcMysqlFileSupport.retrieveFile(slidename, fileName);
    }

}