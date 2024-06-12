import React from "react";
import style from "./SideBar.module.css";
import { SidebarData } from "../data/admin";
import { UilSignOutAlt, UilBars } from "@iconscout/react-unicons";
import { useState } from "react";
import { motion } from "framer-motion";
import { history } from "../App";

const Sidebar = () => {
  const [selected, setSelected] = useState(0);

  const [expanded, setExpaned] = useState(true);

  const sidebarVariants = {
    true: {
      left: "0",
    },
    false: {
      left: "-60%",
    },
  };
  console.log(window.innerWidth);
  return (
    <>
      <div
        className={style.bars}
        style={expanded ? { left: "60%" } : { left: "5%" }}
        onClick={() => setExpaned(!expanded)}
      >
        <UilBars />
      </div>
      <motion.div
        className={style.sidebar}
        variants={sidebarVariants}
        animate={window.innerWidth <= 768 ? `${expanded}` : ""}
      >
        <div className={style.menu}>
          {SidebarData.map((item, index) => {
            return (
              <div
                className={
                  selected === index
                    ? `${style.menuItem} ${style.active}`
                    : style.menuItem
                }
                key={index}
                onClick={() => {
                  setSelected(index)
                  history.push(`/${item.url}`)
                  
                  }}
              >
                <item.icon />
                <span>{item.heading}</span>
              </div>
            );
          })}
          {/* signoutIcon */}
          <div className={style.menuItem}>
            <UilSignOutAlt />
          </div>
        </div>
      </motion.div>
    </>
  );
};

export default Sidebar;
