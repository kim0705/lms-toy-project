import React from 'react';
import * as S from './style';
import { FaBook, FaChartBar, FaBullhorn, FaThLarge, FaArrowLeft, FaUserCircle } from 'react-icons/fa';
import { useLocation, useNavigate, useParams } from 'react-router-dom';

function Sidebar() {
    const location = useLocation();
    const navigate = useNavigate();
    const { courseId } = useParams();

    const isInCourse = location.pathname.includes('/course/');

    const coursesList = [
        { id: '1', name: 'React 초급 마스터' },
        { id: '2', name: 'Node.js 백엔드 입문' },
        { id: '3', name: 'UI/UX 디자인 원론' },
    ];

    const menuList = [
        { name: '강의수강', path: `/course/${courseId}/study`, icon: <FaBook /> },
        { name: '리포트', path: `/course/${courseId}/report`, icon: <FaChartBar /> },
        { name: '공지사항', path: `/course/${courseId}/notice`, icon: <FaBullhorn /> },
    ];

    return (
        <S.SidebarContainer>
            {/* 상단 유저 정보 영역 */}
            <S.ProfileArea>
                <FaUserCircle size={30} />
                <S.UserInfo>
                    <span className="name">홍길동 학생</span>
                </S.UserInfo>
            </S.ProfileArea>

            <S.NavSection>
                {!isInCourse ? (
                    /* 1. 과목 진입 전 (대시보드 모드) */
                    <>
                        <S.MenuLink to="/" className={location.pathname === '/' ? 'active' : ''}>
                            <FaThLarge />
                            <span>대시보드</span>
                        </S.MenuLink>

                        <S.SectionTitle>내 수강 과목</S.SectionTitle>
                        {coursesList.map((course) => (
                            <S.CourseItem
                                key={course.id}
                                onClick={() => navigate(`/course/${course.id}/study`)}
                            >
                                <FaBook /> <span>{course.name}</span>
                            </S.CourseItem>
                        ))}
                    </>
                ) : (
                    /* 2. 특정 과목 진입 후 (학습 모드) */
                    <>
                        <S.MenuLink to="/">
                            <FaArrowLeft />
                            <span>대시보드로 나가기</span>
                        </S.MenuLink>

                        <S.Divider />

                        <S.SectionTitle>다른 과목 선택</S.SectionTitle>
                        <S.CourseSelectWrapper>
                            <select
                                value={courseId}
                                onChange={(e) => navigate(`/course/${e.target.value}/study`)}
                            >
                                {coursesList.map(course => (
                                    <option key={course.id} value={course.id}>{course.name}</option>
                                ))}
                            </select>
                        </S.CourseSelectWrapper>

                        <S.Divider />

                        <S.SectionTitle>학습 메뉴</S.SectionTitle>
                        {menuList?.map((menu) => (
                            <S.MenuLink key={menu.path} to={menu.path}>
                                {menu.icon}
                                <span>{menu.name}</span>
                            </S.MenuLink>
                        ))}
                    </>
                )}
            </S.NavSection>
        </S.SidebarContainer>
    );
}

export default Sidebar;