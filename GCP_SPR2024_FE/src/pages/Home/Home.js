import React, { Fragment } from "react";
import MobileMenu from "./MobileMenu/MobileMenu";
import ThemeShadow from "./ThemeShadow/ThemeShadow";
import HeroBanner from "./HeroBanner/HeroBanner";
import CondtentStart from "./CondtentStart/CondtentStart";
import AboutTap from "./AboutTap/AboutTap";
import AboutArea from "./AboutArea/AboutArea";
import PopulerSection from "./PopulerSection/PopulerSection";
import GridArea from "./GridArea/GridArea";
import RegisterArea from "./RegisterArea/RegisterArea";
import TeamArea from "./TeamArea/TeamArea";
import Testimonial from "./Testimonial/Testimonial";
import BlogArea from "./BlogArea/BlogArea";
import { useSelector } from "react-redux";
import BrandArea from "./BrandArea/BrandArea";

export default function Home() {
  const { user } = useSelector(root => root.AuthReducer)
  console.log(user)
  return (
    <Fragment>
      <MobileMenu />
      <ThemeShadow />
      <HeroBanner />
      <CondtentStart />
      {/* <AboutTap /> */}
      {/* <BrandArea/> */}
      <AboutArea />
      <PopulerSection />
      <GridArea />
      <RegisterArea />
      <TeamArea />
      <Testimonial />
      <BlogArea />
    </Fragment>
  );
}
