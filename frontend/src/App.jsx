import { Navigate, Route, Routes } from "react-router-dom";
import { Global, ThemeProvider } from "@emotion/react";
import { globalStyles } from "./styles/global";
import MainLayout from "./layout/MainLayout/MainLayout";
import { theme } from "./styles/theme";
import Dashboard from "./pages/Dashboard/Dashboard";
import LecturePage from "./pages/LecturePage/LecturePage";
import LoginPage from "./pages/LoginPage/LoginPage";
import NoticePage from "./pages/NoticePage/NoticePage";
import NoticeDetailPage from "./pages/NoticeDetailPage/NoticeDetailPage";

/* 인증 보호 라우트: Access Token 없으면 로그인 페이지로 리다이렉트 */
function ProtectedRoute({ children }) {
    const token = localStorage.getItem("accessToken");
    return token ? children : <Navigate to="/login" replace />;
}

function App() {
    return (
        <>
            <ThemeProvider theme={theme}>
                <Global styles={globalStyles} />
                <Routes>
                    <Route path="/login" element={<LoginPage />} />

                    <Route element={<ProtectedRoute><MainLayout /></ProtectedRoute>}>
                        <Route path="/" element={<Dashboard />} />

                        <Route path="/course/:courseId">
                            <Route path="study" element={<LecturePage />} />
                            <Route path="report" element={<div>리포트 페이지</div>} />
                            <Route path="notice" element={<NoticePage />} />
                            <Route path="notice/:noticeId" element={<NoticeDetailPage />} />
                        </Route>
                    </Route>
                </Routes>
            </ThemeProvider>
        </>
    );
}

export default App;
