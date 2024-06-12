import React from "react";
import { Link } from "react-router-dom/cjs/react-router-dom";
import { menu_data } from "../../../data";
const NavMenu = () => {
  return (
    <>
      <ul>
        {menu_data.map((link, index) => (
          <li key={index} className={link.submenu ? "has-dropdown" : ""}>
            <Link to={`${link.link}`}>{link.title}</Link>
            {link.submenu && (
              <ul className="submenu">
                {link.submenu.map((menu) => (
                  <li key={menu.id}>
                    <Link to={`${menu.link}`}>{menu.title}</Link>
                  </li>
                ))}
              </ul>
            )}
          </li>
        ))}
      </ul>
    </>
  );
};

export default NavMenu;
