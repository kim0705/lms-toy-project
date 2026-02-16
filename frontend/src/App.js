import { Route, Routes } from "react-router-dom";
import { Global, ThemeProvider } from "@emotion/react";
import { globalStyles } from "./styles/global";
import MainLayout from "./layout/MainLayout/MainLayout";
import { theme } from "./styles/theme";
import CoursesPage from "./pages/CoursesPage/CoursesPage";
import Dashboard from "./pages/Dashboard/Dashboard";


function App() {
    return (
        <>
            <ThemeProvider theme={theme}>
                <Global styles={globalStyles} />
                <Routes>
                    <Route element={<MainLayout />}>
                        <Route path="/" element={<Dashboard />} />

                        <Route path="/course/:courseId">
                            <Route path="study" element={<CoursesPage />} />
                            <Route path="report" element={<div>리포트 페이지</div>} />
                            <Route path="notice" element={<div>공지사항 페이지</div>} />
                        </Route>
                    </Route>
                </Routes>
            </ThemeProvider>
        </>
    );
}

export default App;
