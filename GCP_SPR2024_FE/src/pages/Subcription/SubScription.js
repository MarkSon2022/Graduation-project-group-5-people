import React, { useState } from "react";
import "./SubCription.css";
import { useHistory, useLocation } from "react-router-dom";
import axios from "axios";
import { BASE_URL } from "../../data/const";
import "../../components/Course/CourseDetail.css";
const SubScription = () => {
  const history = useHistory();
  const location = useLocation();
  const [subscriptionData, setSubscriptionData] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const { courseDetail } = location.state;
  const coursePackages = courseDetail.courseCoursePackages.map((course) => ({
    id: course.id,
    maxStudent: course.maxStudent,
    name: course.name,
    price: course.price,
  }));

  const handleSubscribe = (price, id, maxStudent) => {
    setIsLoading(true);
    const subscriptionRequestDTO = {
      startDate: new Date().toISOString(),
      endDate: new Date().toISOString(),
      boughtPrice: price,
      boughtMaxStudent: maxStudent,
      status: "PENDING",
      coursePackageId: id,
      organizationId: JSON.parse(localStorage.getItem("USER")).userId,
    };

    axios
      .post(BASE_URL + "api/v1/subscription", subscriptionRequestDTO)
      .then((response) => {
        const paymentUrl = response.data.paymentUrl;
        console.log(paymentUrl);
        // Navigate to URL
        window.location = paymentUrl;
        setSubscriptionData(response.data);
      })
      .catch((error) => {
        console.error("Error subscribing:", error);
      })
      .finally(() => {
        setIsLoading(false); // End loading
      });
  };
  if (isLoading) {
    return (
      <div className="loading-container">
        <div className="loading-spinner"></div>
        <div>Loading...</div>
      </div>
    );
  }
  return (
    <div className="col-xl-9 col-lg-9 col-md-8 col-12 mx-auto">
      <div
        className="tab-content tab__content__wrapper with__sidebar__content"
        id="myTabContent"
      >
        <div
          className="tab-pane fade  active show"
          id="projects__one"
          role="tabpanel"
          aria-labelledby="projects__one"
        >
          <div className="row">
            {coursePackages.map((coursePackage) => (
              <div
                className="col-xl-4 col-lg-6 col-md-12 col-sm-6 col-12"
                data-aos="fade-up"
              >
                <div className="gridarea__wraper gridarea__wraper__2">
                  {/* Subscription Plan Image */}
                  <div className="gridarea__img">
                    <img
                      loading="lazy"
                      src="https://thumbs.dreamstime.com/b/shopping-bag-logo-design-icon-online-shop-symbol-vector-illustrations-discount-flat-sales-digital-commerce-store-simple-marketing-157881334.jpg"
                      alt="Subscription Plan"
                    />
                  </div>
                  {/* Subscription Plan Details */}
                  <div className="gridarea__content">
                    {/* Subscription Plan Name */}
                    <div className="gridarea__heading">
                      <h3>{coursePackage.name}</h3>
                    </div>
                    {/* Number of Students */}
                    <div className="gridarea__students">
                      <p>{coursePackage.maxStudent} Students</p>
                    </div>
                    {/* Subscription Plan Price */}
                    <div className="gridarea__price">
                      {coursePackage.price} VND per 6 month
                    </div>
                    {/* Subscribe Button */}
                    <div className="gridarea__bottom">
                      <button
                        className="subscribe-button"
                        onClick={() => {
                          handleSubscribe(
                            coursePackage.price,
                            coursePackage.id,
                            coursePackage.maxStudent
                          );
                        }}
                      >
                        Subscribe Now
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
      <div className="main__pagination__wrapper" data-aos="fade-up">
        <ul className="main__page__pagination">
          <li>
            <a className="disable" href="#">
              <i className="icofont-double-left" />
            </a>
          </li>
          <li>
            <a className="active" href="#">
              1
            </a>
          </li>
          <li>
            <a href="#">2</a>
          </li>
          <li>
            <a href="#">3</a>
          </li>
          <li>
            <a href="#">
              <i className="icofont-double-right" />
            </a>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default SubScription;
