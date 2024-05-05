package project.tubespbo.Controllers.MainPage;

import project.tubespbo.Models.LoginModel;

public class MainPageController {

    LoginModel loginModel = new LoginModel();

    public void initialize() {
        System.out.println(loginModel.getRole() );
    }

}
