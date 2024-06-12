package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.CoursePackageRequestDTO;
import sse.edu.SPR2024.dto.response.CoursePackageResponseDTO;
import sse.edu.SPR2024.entity.Course;
import sse.edu.SPR2024.entity.CoursePackage;
import sse.edu.SPR2024.repository.ICoursePackageRepository;
import sse.edu.SPR2024.service.ICoursePackageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoursePackageService implements ICoursePackageService {
    private final ICoursePackageRepository coursePackageRepository;
    private final ModelMapper modelMapper;
    @Override
    public CoursePackageResponseDTO saveCoursePackage(CoursePackageRequestDTO coursePackageRequestDTO) {
        CoursePackage coursePackage = modelMapper.map(coursePackageRequestDTO, CoursePackage.class);

        return modelMapper.map(coursePackageRepository.save(coursePackage), CoursePackageResponseDTO.class);
    }

    @Override
    public List<CoursePackageResponseDTO> saveListCoursePackage(Course course, List<CoursePackageRequestDTO> coursePackageRequestDTOs) {
        List<CoursePackage> coursePackages = coursePackageRequestDTOs
                .stream()
                .map(element -> {
                    CoursePackage coursePackage = modelMapper.map(element, CoursePackage.class);
                    coursePackage.setCourse(course);
                    return coursePackage;
                })
                .collect(Collectors.toList());

        List<CoursePackage> coursePackagesAll = coursePackageRepository.saveAll(coursePackages);

        List<CoursePackageResponseDTO> coursePackageResponseDTOs = coursePackagesAll
                .stream()
                .map(element -> modelMapper.map(element, CoursePackageResponseDTO.class))
                .collect(Collectors.toList());

        return coursePackageResponseDTOs;
    }

    @Override
    public List<CoursePackage> getCoursePackageMappedWithCourse(Course course, List<CoursePackageRequestDTO> coursePackageRequestDTOs) {
        return coursePackageRequestDTOs
                .stream()
                .map(element -> {
                    CoursePackage coursePackage = modelMapper.map(element, CoursePackage.class);
                    return coursePackage.getCoursePackageToCourse(course);
                })
                .collect(Collectors.toList());
    }


}
