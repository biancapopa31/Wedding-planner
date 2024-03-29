public class App {
    public static Wedding wedding = new Wedding();
    public static void main(String[] args) throws Exception {

        Service service = new Service();
        int input = service.MainMenu();

        switch(input){
            case 1:
                service.generalInformationMeniu();
                break;
            case 2:
                service.exit();
                break;

            default:
                System.out.println("Invalid input!");
                break;
        }
    }
}
