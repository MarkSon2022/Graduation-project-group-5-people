import React from "react";

export default function BlogArea() {
  return (
    <div className="blogarea sp_bottom_70 sp_top_20">
      <div className="container">
        <div className="row" data-aos="fade-up">
          <div className="col-xl-12">
            <div className="section__title text-center">
              <div className="section__title__button">
                <div className="default__small__button">News &amp; Blogs</div>
              </div>
              <div className="section__title__heading">
                <h2>Leatest News &amp; Blog</h2>
              </div>
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-xl-8 col-lg-8" data-aos="fade-up">
            <div className="blogarea__content__wraper">
              <div className="blogarea__img">
                <img loading="lazy" src="img/blog/blog_1.png" alt="blog" />
                <div className="blogarea__date">
                  20
                  <span>Oct</span>
                </div>
              </div>
              <div className="blogarea__text__wraper">
                <h3>
                  <a href="#">
                    It is a long established fact that a reader will be Standard
                    Part
                  </a>
                </h3>
                <div className="blogarea__para">
                  <p>
                    A wonderful serenity has taken possssion of my entire souing
                    like these sweet morning spring whch enjoy with my whole
                    heart I am alone, and feel the charm of existenceths spot
                    whch was create For the bliss of souls like mineing am so
                    happy my dear frend so absori bed in the exquste sens of
                    mere. A wonderful serenity has taken posseson of my entire
                    soung like these sweet mornngs spring whch enjoy …
                  </p>
                </div>
                <div className="blogarea__icon">
                  <div className="blogarea__person">
                    <div className="blogarea__img">
                      <img loading="lazy" src="img/blog/blog_2.png" alt />
                    </div>
                    <div className="blogarea__name">
                      <span> By</span>: Mirnsdo Jons
                    </div>
                  </div>
                  <div className="blogarea__list">
                    <ul>
                      <li>
                        <a href="#">
                          <i className="icofont-facebook" />
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i className="icofont-youtube-play" />
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i className="icofont-instagram" />
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i className="icofont-twitter" />
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-xl-4 col-lg-4" data-aos="fade-up">
            <div className="blogarea__content__wraper">
              <div className="blogarea__img">
                <img loading="lazy" src="img/blog/blog_3.png" alt="blog" />
                <div className="blogarea__date small__date">
                  20
                  <span>Oct</span>
                </div>
              </div>
              <div className="blogarea__text__wraper blogarea__text__wraper__2">
                <h3>
                  <a href="#">
                    It is a long established fact that a reader will be Standard
                    Part
                  </a>
                </h3>
              </div>
            </div>
            <div className="blogarea__content__wraper">
              <div className="blogarea__img">
                <img loading="lazy" src="img/blog/blog_4.png" alt="blog" />
                <div className="blogarea__date small__date">
                  20
                  <span>Oct</span>
                </div>
              </div>
              <div className="blogarea__text__wraper blogarea__text__wraper__2">
                <h3>
                  <a href="#">
                    It is a long established fact that a reader will be Standard
                    Part
                  </a>
                </h3>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
