/**
 * @Note
 * The default export of _app.js is a top-level React component that wraps all the pages
 * in your application. You can use this component to keep state when navigating between pages,
 * or to add global styles.
*/
import '../styles/globals.scss';
import { UserProvider } from '@auth0/nextjs-auth0/client';

export default function App({ Component, pageProps }) {
    return (
      <UserProvider>
        <Component {...pageProps} />
      </UserProvider>
    );
  }
