import styled from '@emotion/styled';

export const Container = styled.div`
    width: 100%;
`;

export const Header = styled.div`
    margin-bottom: 32px;
`;

export const Title = styled.h2`
    font-size: 28px;
    font-weight: 800;
    color: ${({ theme }) => theme.colors.textMain};
`;

export const Subtitle = styled.p`
    font-size: 16px;
    color: ${({ theme }) => theme.colors.textSub};
    margin-top: 8px;
`;

export const CardGrid = styled.div`
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
    gap: 24px;
`;

export const CourseCard = styled.div`
    background: ${({ theme }) => theme.colors.white};
    border-radius: 16px;
    overflow: hidden;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
`;

export const CardImage = styled.img`
    width: 100%;
    height: 160px;
    object-fit: cover;
`;

export const CardContent = styled.div`
    padding: 24px;
`;

export const Instructor = styled.div`
    font-size: 14px;
    color: ${({ theme }) => theme.colors.primary};
    font-weight: 600;
`;

export const CourseTitle = styled.h3`
    font-size: 18px;
    font-weight: 700;
    margin: 8px 0 20px;
    height: 48px;
    line-height: 1.4;
`;

export const ProgressWrapper = styled.div`
    margin-bottom: 24px;
`;

export const ProgressText = styled.div`
    display: flex;
    justify-content: space-between;
    font-size: 13px;
    margin-bottom: 8px;
`;

export const ProgressBar = styled.div`
    height: 8px;
    background: #eee;
    border-radius: 4px;
`;

export const ProgressFill = styled.div`
    width: ${({ width }) => width}%;
    height: 100%;
    background: ${({ theme }) => theme.colors.primary};
    border-radius: 4px;
`;

export const CardFooter = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-top: 1px solid #f1f5f9;
    padding-top: 16px;
`;

export const LastDate = styled.div`
    font-size: 13px;
    color: #94a3b8;
    display: flex;
    align-items: center;
    gap: 4px;
`;

export const LearnButton = styled.button`
    padding: 10px 16px;
    border-radius: 8px;
    font-weight: 600;
    font-size: 14px;
    display: flex;
    align-items: center;
    gap: 6px;
    background: ${({ theme, isDone }) => isDone ? '#f0fdf4' : theme.colors.primary};
    color: ${({ theme, isDone }) => isDone ? '#16a34a' : 'white'};
`;

export const SectionDivider = styled.hr`
    margin: 48px 0;
    border: 0;
    border-top: 1px solid ${({ theme }) => theme.colors.border};
`;