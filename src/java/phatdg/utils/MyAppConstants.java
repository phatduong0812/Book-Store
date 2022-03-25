/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.utils;

/**
 *
 * @author Kieu Trong Khanh
 */
public class MyAppConstants {

    public class LoginFeatures {

        public static final String INVALID_PAGE = "invalidPage";
        public static final String PROFILE_PAGE = "profilePage";
    }

    public class SearchAccountFeatures {

        public static final String SEARCH_PAGE = "searchPage";
    }

    public class DeleteAccountFeatures {

        public static final String ERROR_PAGE = "errorPage";
        public static final String SEARCH_ACCTION = "searchAction";
    }

    public class UpdateAccountFeatures {

        public static final String ERROR_PAGE = "errorPage";
        public static final String SEARCH_ACCTION = "searchAction";
        public static final String PROFILE_PAGE = "profilePage";
    }

    public class RegisterFeatures {

        public static final String REGISTER_PAGE = "registerPage";
        public static final String LOGIN_PAGE = "loginPage";
    }

    public class StoreViewFeatures {

        public static final String ERROR_PAGE = "errorPage";
        public static final String STORE_PAGE = "storePage";
    }

    public class AddItemsFeatures {

        public static final String STORE_PAGE = "storePage";
    }

    public class RemoveItemsFutures {

        public static final String VIEW_CART_ACTION = "cartPage";
    }

    public class LogoutFeatures {

        public static final String LOGIN_PAGE = "loginPage";
    }

    public class StartUpServlet {

        public static final String LOGIN_PAGE = "loginPage";
        public static final String PROFILE_PAGE = "profilePage";
    }

    public class GoogleServlet {

        public static final String CLIENT_ID = "534066569161-qpsi3f3u020"
                + "vi5r297tlobemhgnbve4k.apps.googleusercontent.com";
        
        public static final String CLIENT_SECRET = "GOCSPX-eZO4J9RyHzdJ"
                + "7f9yCO1PP_dKL8Gd";
        
        public static final String REDIRECT_URI = "http://localhost:8084/Projec"
                + "tFilterMVC2Template/loginGoogle";
        
        public static final String LINK_GET_TOKEN = "https://oauth2.goog"
                + "leapis.com/token";
        
        public static final String LINK_GET_USER_INFO = "https://www.goog"
                + "leapis.com/oauth2/v1/userinfo?access_token=";
        
        public static final String GRANT_TYPE = "authorization_code";
        
        public static final String LOGIN_PAGE = "loginPage";
        
        public static final String PROFILE_PAGE = "profilePage";
    }
    
    public class UpdateProfileFeatures {
        public static final String ERROR_PAGE = "errorPage";
        
        public static final String PROFILE_PAGE = "profilePage";
    }
    
    public class ViewCartFeatures {
        public static final String CART_PAGE = "cartPage";
    }
    
    public class CheckOutFeatures {
        public static final String CART_PAGE = "cartPage";
        public static final String SUCCESS_CHECKOUT = "storePage";
    }
}
