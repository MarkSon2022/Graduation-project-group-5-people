import React from "react";
import useSticky from "../../../hooks/use-sticky";
import { useDispatch } from "react-redux";
import { Link } from "react-router-dom/cjs/react-router-dom";
import CatDot from "../../../svg/cat-dot";
import NavMenu from "./NavMenu";

export const cat_submenu = [
  "English Learning",
  "Web Development",
  "Logo Design",
  "Motion Graphics",
  "Video Edition",
];

const Header1 = ({ header_white, header_shadow }) => {
  const { sticky } = useSticky();
  const dispatch = useDispatch();

  return (
    <>
      <header>
        <div
          id="header-sticky"
          className={`header__area 
        ${
          header_shadow
            ? "header__padding-2 header__shadow"
            : "header__transparent header__padding"
        } 
        ${header_white ? "header__white" : ""} ${sticky ? "sticky" : ""}`}
        >
          <div className={`${header_shadow ? "container" : "container-fluid"}`}>
            <div className="row align-items-center">
              <div className="col-xxl-3 col-xl-3 col-lg-4 col-md-2 col-sm-4 col-6">
                <div className="header__left d-flex">
                  <div className="logo">
                    <Link to="/">
                      <img
                        src={
                          header_white && !sticky
                            ? "img/logo/logo_1.png"
                            : "img/logo/logo_1.png"
                        }
                        alt="logo"
                      />
                    </Link>
                  </div>
                  <div className="header__category d-none d-lg-block">
                    <nav>
                      <ul>
                        <li>
                          <Link
                            to="/courses"
                            className="cat-menu d-flex align-items-center"
                          >
                            <div className="cat-dot-icon d-inline-block">
                              <CatDot />
                            </div>
                            <span>Category</span>
                          </Link>
                          <ul className="cat-submenu">
                            {cat_submenu.map((category, index) => (
                              <li key={index}>
                                <Link to="/course-details">{category}</Link>
                              </li>
                            ))}
                          </ul>
                        </li>
                      </ul>
                    </nav>
                  </div>
                </div>
              </div>
              <div className="col-xxl-9 col-xl-9 col-lg-8 col-md-10 col-sm-8 col-6">
                <div className="header__right d-flex justify-content-end align-items-center">
                  <div
                    className={`${
                      header_white
                        ? "main-menu main-menu-3 d-none d-xl-block"
                        : header_shadow
                        ? "main-menu main-menu-2 d-none d-xl-block"
                        : "main-menu d-none d-xl-block"
                    }`}
                  >
                    <nav id="mobile-menu">
                      <NavMenu />
                    </nav>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>
    </>
  );
};

export default Header1;
