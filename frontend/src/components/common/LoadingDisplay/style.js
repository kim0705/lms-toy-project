import { keyframes } from "@emotion/react";
import styled from "@emotion/styled";

const rotate = keyframes`
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
`;

export const StatusWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 80px 0;
    width: 100%;
    color: ${({ theme }) => theme.colors.textSub}; 

    p { 
        margin-top: 20px; 
        font-size: 15px; 
        font-weight: 500;
    }
    
    svg { 
        font-size: 48px; 
        color: ${({ theme }) => theme.colors.border}; 
    }
`;

export const Spinner = styled.div`
    width: 45px;
    height: 45px;
    border: 4px solid ${({ theme }) => theme.colors.border};
    border-top: 4px solid ${({ theme }) => theme.colors.primary}; 
    border-radius: 50%;
    animation: ${rotate} 1s linear infinite;
`;