import styled from '@emotion/styled';

export const Container = styled.div`
    padding: 10px;
`;

export const Header = styled.div`
    margin-bottom: 30px;
`;

export const Title = styled.h2`
    font-size: 28px;
    font-weight: 800;
    color: ${(props) => props.theme.colors.textMain};
    margin-bottom: 8px;
`;

export const Subtitle = styled.p`
    color: ${(props) => props.theme.colors.textSub};
    font-size: 16px;
`;

export const CardGrid = styled.div`
    display: grid;
    /* 화면 너비에 따라 자동으로 카드 개수 조절 */
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 24px;
`;

export const CourseCard = styled.div`
    background: white;
    border-radius: 16px;
    overflow: hidden;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
    transition: transform 0.2s ease;

    &:hover {
        transform: translateY(-5px);
    }
`;

export const CardImage = styled.img`
    width: 100%;
    height: 160px;
    object-fit: cover;
`;

export const CardContent = styled.div`
    padding: 20px;
`;

export const Instructor = styled.span`
    font-size: 13px;
    color: ${(props) => props.theme.colors.primary};
    font-weight: 600;
`;

export const CourseTitle = styled.h3`
    font-size: 18px;
    font-weight: 700;
    margin: 8px 0 20px;
    color: ${(props) => props.theme.colors.textMain};
    height: 44px; /* 두 줄 제한 */
    overflow: hidden;
`;

export const ProgressWrapper = styled.div`
    margin-bottom: 20px;
`;

export const ProgressText = styled.div`
    display: flex;
    justify-content: space-between;
    font-size: 13px;
    margin-bottom: 8px;
    font-weight: 600;
    color: ${(props) => props.theme.colors.textSub};
`;

export const ProgressBar = styled.div`
    width: 100%;
    height: 8px;
    background-color: #eee;
    border-radius: 4px;
    overflow: hidden;
`;

export const ProgressFill = styled.div`
    width: ${(props) => props.width}%;
    height: 100%;
    background-color: ${(props) => props.theme.colors.primary};
    transition: width 0.5s ease-in-out;
`;

export const CardFooter = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-top: 15px;
    border-top: 1px solid #f0f0f0;
`;

export const LastDate = styled.div`
    font-size: 12px;
    color: #999;
    display: flex;
    align-items: center;
    gap: 4px;
`;

export const LearnButton = styled.button`
    padding: 8px 16px;
    border-radius: 8px;
    font-size: 14px;
    font-weight: 600;
    display: flex;
    align-items: center;
    gap: 6px;
    
    background-color: ${(props) => (props.isDone ? '#f0fdf4' : props.theme.colors.primary)};
    color: ${(props) => (props.isDone ? '#16a34a' : 'white')};
    
    &:hover {
        opacity: 0.9;
    }
`;