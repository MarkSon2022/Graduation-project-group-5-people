import React, { useEffect } from "react";
import "./SubCription.css";
import { useLocation } from "react-router-dom";
import axios from "axios";
import { BASE_URL } from "../../data/const";

const ConfirmationPage = () => {
  const location = useLocation();

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const url = `${BASE_URL}api/v1/subscription/pay-status?${params.toString()}`;

    axios
      .get(url)
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [location]);

  return (
    <div className="confirmation-page">
      <h2>Thank You For Your Purchase!</h2>
      <p>
        Your subscription to has been successfully processed. You will receive
        an email confirmation shortly.
      </p>
      <button onClick={() => (window.location.href = "/")}>Go to Home</button>
    </div>
  );
};

export default ConfirmationPage;
