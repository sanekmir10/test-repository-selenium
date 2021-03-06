package pages.adminPages;

import org.openqa.selenium.WebDriver;
import pages.AbstrPage;
import webElements.adminElements.SidebarElements;


public class AbstrAdminPage extends AbstrPage {

    public AbstrAdminPage(WebDriver driver) {
        super(driver);
    }

    // Добавляем боковое меню
    protected SidebarElements sidebar;

    public SidebarElements sidebar() {
        // Инициализируем элементы, если еще не инициализированны
        if (this.sidebar == null) {
            this.sidebar = new SidebarElements(driver);
        }
        return this.sidebar;
    }


}
