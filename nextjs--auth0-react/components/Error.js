import React from 'react';

export function ErrorComponent({ message }) {
  return (
    <div className="alert alert-danger" role="alert">
      Oops... {message}
    </div>
  );
}