import React, { useState, useEffect } from "react";

export default function Sidebar({ data, setDataFiltered }) {
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [selectedAge, setSelectedAge] = useState(null);
  const [selectedProjectName, setSelectedProjectName] = useState(null);
  const [searchText, setSearchText] = useState("");
  const [minAgeInput, setMinAgeInput] = useState("3");
  const [maxAgeInput, setMaxAgeInput] = useState("30");

  // Extracting unique skills from the provided data
  const uniqueSkills = Array.from(
    new Set(
      data
        .map((course) =>
          course.project.skill.split(", ").map((skill) => skill.trim())
        )
        .flat()
    )
  );

  // Extracting unique ages from the provided data
  const uniqueAges = Array.from(new Set(data.map((course) => course.age)));

  // Extracting unique project names from the provided data
  const uniqueProjectNames = Array.from(
    new Set(data.map((course) => course.project.goal))
  );

  const handleSkillToggle = (skill) => {
    const updatedSkills = selectedSkills.includes(skill)
      ? selectedSkills.filter((selectedSkill) => selectedSkill !== skill)
      : [...selectedSkills, skill];
    setSelectedSkills(updatedSkills);
    filterCourses(updatedSkills, selectedAge, selectedProjectName, searchText, minAgeInput, maxAgeInput);
  };

  const handleAgeSelect = (age) => {
    setSelectedAge(age);
    filterCourses(selectedSkills, age, selectedProjectName, searchText, minAgeInput, maxAgeInput);
  };

  const handleProjectNameSelect = (projectName) => {
    setSelectedProjectName(projectName);
    filterCourses(selectedSkills, selectedAge, projectName, searchText, minAgeInput, maxAgeInput);
  };

  const handleSearch = (text) => {
    setSearchText(text);
    filterCourses(selectedSkills, selectedAge, selectedProjectName, text, minAgeInput, maxAgeInput);
  };

  const handleMinAgeInputChange = (event) => {
    setMinAgeInput(event.target.value);
    filterCourses(selectedSkills, selectedAge, selectedProjectName, searchText, event.target.value, maxAgeInput);
  };

  const handleMaxAgeInputChange = (event) => {
    setMaxAgeInput(event.target.value);
    filterCourses(selectedSkills, selectedAge, selectedProjectName, searchText, minAgeInput, event.target.value);
  };

  // Function to filter courses based on skills, age, project name, age recommendation, and search text
  const filterCourses = (skills, age, projectName, searchText, minAge, maxAge) => {
    let filteredCourses = data;

    if (skills.length > 0) {
      filteredCourses = filteredCourses.filter((course) =>
        skills.some((skill) =>
          course.project.skill.split(", ").map((s) => s.trim()).includes(skill)
        )
      );
    }

    if (age !== null) {
      filteredCourses = filteredCourses.filter((course) => course.age === age);
    }

    if (projectName !== null) {
      filteredCourses = filteredCourses.filter((course) => course.project.goal === projectName);
    }

    if (minAge !== "" && maxAge !== "") {
      filteredCourses = filteredCourses.filter((course) => {
        const courseMinAge = parseInt(course.project.ageRecomment.split("-")[0]);
        const courseMaxAge = parseInt(course.project.ageRecomment.split("-")[1]);
        const userMinAge = parseInt(minAge);
        const userMaxAge = parseInt(maxAge);
        return userMinAge <= courseMaxAge && userMaxAge >= courseMinAge;
      });
    }

    if (searchText.trim() !== "") {
      filteredCourses = filteredCourses.filter((course) =>
        course.name.toLowerCase().includes(searchText.toLowerCase())
      );
    }

    setDataFiltered(filteredCourses);
  };

  return (
    <>
      <div className="col-xl-3 col-lg-3 col-md-4 col-12">
        <div className="course__sidebar__wraper" data-aos="fade-up">
          <div className="course__heading">
            <h5>Search here</h5>
          </div>
          <div className="course__input">
            <input
              type="text"
              placeholder="Search course"
              value={searchText}
              onChange={(e) => handleSearch(e.target.value)}
            />
            <div className="search__button">
              <button>
                <i className="icofont-search-1" />
              </button>
            </div>
          </div>
        </div>
        <div className="course__sidebar__wraper" data-aos="fade-up">
          <div className="categori__wraper">
            <div className="course__heading">
              <h5>Categories</h5>
            </div>
            <div className="course__categories__list">
              <ul>
                <li>
                  <a
                    onClick={() => handleSkillToggle(null)}
                    className={selectedSkills.length === 0 ? "active" : ""}
                  >
                    All
                  </a>
                </li>
                {uniqueSkills.map((skill, index) => (
                  <li key={index}>
                    <a
                      onClick={() => handleSkillToggle(skill)}
                      className={selectedSkills.includes(skill) ? "active" : ""}
                    >
                      {skill}
                    </a>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>
        <div className="course__sidebar__wraper" data-aos="fade-up">
          <div className="course__heading">
            <h5>Skill Level</h5>
          </div>
          <div className="course__skill__list">
            <ul>
              <li>
                <a
                  onClick={() => handleAgeSelect(null)}
                  className={selectedAge === null ? "active" : ""}
                >
                  All
                </a>
              </li>
              {uniqueAges.map((age, index) => (
                <li key={index}>
                  <a
                    onClick={() => handleAgeSelect(age)}
                    className={selectedAge === age ? "active" : ""}
                  >
                    {age}
                  </a>
                </li>
              ))}
            </ul>
          </div>
        </div>
        <div className="course__sidebar__wraper" data-aos="fade-up">
          <div className="course__heading">
            <h5>Age Range</h5>
          </div>
          <div>
            <input
              type="number"
              placeholder="Min Age"
              value={minAgeInput}
              onChange={handleMinAgeInputChange}
                style={{marginBottom: '10px', width: '100%', padding: '5px 15px', border: '1px solid var(--borderColor)' }}
            />
            <input
              type="number"
              placeholder="Max Age"
              value={maxAgeInput}
              onChange={handleMaxAgeInputChange}
              style={{marginBottom: '10px', width: '100%', padding: '5px 15px', border: '1px solid var(--borderColor)' }}

            />
          </div>
        </div>
        <div className="course__sidebar__wraper" data-aos="fade-up">
          <div className="course__heading">
            <h5>Project Name</h5>
          </div>
          <div className="course__categories__list">
            <ul>
            <li>
                  <a
                    onClick={() => handleProjectNameSelect(null)}
                    className={selectedProjectName === null ? "active" : ""}
                  >
                    All
                  </a>
                </li>
                {uniqueProjectNames.map((projectName, index) => (
                  <li key={index}>
                    <a
                      onClick={() => handleProjectNameSelect(projectName)}
                      className={selectedProjectName === projectName ? "active" : ""}
                    >
                      {projectName}
                    </a>
                  </li>
                ))}
              </ul>
            </div>
          </div>
      </div>
    </>
  );
}
