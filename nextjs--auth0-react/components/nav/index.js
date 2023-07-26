import React from 'react';
import Link from 'next/link';
import { useAuth0 } from '@auth0/auth0-react';
import { useRouter } from 'next/router';
import styles from './Nav.module.scss';
import { Loading } from '@/components/Loading';

export function Nav() {
  const { isAuthenticated, isLoading, user, loginWithRedirect, logout } =
    useAuth0();
  const { pathname } = useRouter();

  if (isLoading) {
    return <Loading />;
  }

  const isHomePage = pathname === "/"
  const homeLink = !isHomePage

  return (
    <nav className={styles.nav}>
      {isAuthenticated ? (
        <>
          <div>
            <span id="hello">Hello, {user.name}!</span>{' '}
            <button
              className={styles.authButton}
              id="logout"
              onClick={() =>
                logout({ logoutParams: { returnTo: window.location.origin } })
              }
            >
              logout
            </button>
          </div>
          { homeLink && (
            <div className={styles.homeLinkWrapper}>
              <Link href="/" className={styles.homeLink}> ← Home </Link>
            </div>)}
        </>
      ) : (
        <button
          className={styles.authButton}
          id="login"
          onClick={() => loginWithRedirect()}
        >
          login
        </button>
      )}
    </nav>
  );
}
