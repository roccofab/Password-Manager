import Controller.AppController;
import View.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AppController appController = new AppController(null);
        new StartWindow(appController);
    }
}
