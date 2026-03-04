import { css } from '@emotion/react';

export const globalStyles = css`
    /* 1. 웹 폰트 불러오기 */
    @import url('https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/static/pretendard.min.css');

    html, body, #root {
      width: 100%;
      height: 100%;
      /* 2. 기본 텍스트 렌더링 최적화 */
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    } 

    body {
        /* 3. 전체 기본 폰트 적용 (Pretendard -> 시스템 폰트 순서) */
        font-family: 'Pretendard', -apple-system, BlinkMacSystemFont, system-ui, Roboto, 'Helvetica Neue', 'Segoe UI', 'Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', sans-serif;
        line-height: 1.5;
    }

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        /* 4. 모든 요소에 폰트 상속 강제 */
        font-family: inherit; 
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
        font-family: inherit; /* 버튼에도 폰트 상속 */
        outline: none;
    }
    
    input, textarea {
        font-family: inherit; /* 입력창에도 폰트 상속 */
    }
`;