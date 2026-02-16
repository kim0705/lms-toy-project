import { css } from '@emotion/react';

export const globalStyles = css`
    
    html, body, #root {
      width: 100%;
      height: 100%;
    } 

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    a {
        text-decoration: none;
        color: inherit;
    }

    ul, li {
        list-style: none;
    }

    button {
        cursor: pointer;
        border: none;
        background: none;
    }
`;