// UserProvider.js
import React, { useState, useEffect } from "react";
import Context from "./Context";

export default function UserProvider({ children }) {
  const [user, setUser] = useState(JSON.parse(localStorage.getItem("USER")));

  useEffect(() => {
    localStorage.setItem("USER", JSON.stringify(user));
  }, [user]);

  return (
    <Context.Provider value={{ user, setUser }}>{children}</Context.Provider>
  );
}
