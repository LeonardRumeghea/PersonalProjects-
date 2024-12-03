import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class GameBoard_Loader {

    public static void saveAsXML(GameBoard catalog, String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GameBoard.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(catalog, new File(path));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static GameBoard loadFromXML(String path){

        GameBoard catalog = null;
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(GameBoard.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            catalog = (GameBoard) unmarshaller.unmarshal(new File(path));
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

        return catalog;
    }
}
