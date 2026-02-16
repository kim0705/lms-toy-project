import styled from '@emotion/styled';

export const LayoutWrapper = styled.div`
    display: flex;
    width: 100%;
    min-height: 100vh;
`;

export const ContentArea = styled.div`
    flex: 1;
    display: flex;
    flex-direction: column;
    background-color: ${(props) => props.theme.colors.background};
    min-width: 0;
`;

export const MainContent = styled.main`
    flex: 1;
    padding: 40px;
    overflow-y: auto;
    height: 100vh; 
`;