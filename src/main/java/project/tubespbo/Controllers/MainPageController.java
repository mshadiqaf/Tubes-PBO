package project.tubespbo.Controllers;

import project.tubespbo.Models.LoginModel;

public class MainPageController {

    LoginModel loginModel = new LoginModel();

    public void initialize() {
        System.out.println(loginModel.getRole() );
    }

}
