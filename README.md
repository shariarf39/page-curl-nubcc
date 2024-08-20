# Project Title

## Description
This is a description of the project.

## Images
### Curl Page
<div style="display: flex; justify-content: center;">
    <img src="git_img1.jpeg" alt="Curl page" width="200" style="margin-right: 10px;" />
    <img src="git_img2.jpeg" alt="Curl page" width="200" />
</div>


Creating documentation for your `ZoomableCurlView` project on GitHub will help others understand how to use your library effectively. Below is a suggested structure for your README.md file:

---

# ZoomableCurlView

`ZoomableCurlView` is an Android custom view that extends the functionality of a page curl effect by adding pinch-to-zoom capabilities. This library allows users to interact with content by curling pages like a book while also enabling zooming in and out of the content.

## Features

- **Page Curl Effect**: Provides a realistic page curl animation.
- **Pinch-to-Zoom**: Users can zoom in and out of the content with a simple pinch gesture.
- **Customizable Zoom Levels**: Define minimum and maximum zoom levels to suit your content.

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/shariarf39/page-curl.git
```

### 2. Add to Your Project

Copy the `ZoomableCurlView.java` and `CurlView.java` files into your Android project under the appropriate package (e.g., `com.fahimshariar.pagecurlnub`).

### 3. Add Dependencies

Ensure you have the necessary Android SDK versions and dependencies in your `build.gradle` file:

```groovy
android {
    compileSdkVersion 33
    minSdkVersion 16
    targetSdkVersion 33

    // Other configurations
}

dependencies {
    implementation 'androidx.core:core-ktx:1.10.1'
    implementation 'androidx.appcompat:appcompat:1.7.0'
    // Other dependencies
}
```

## Usage

### 1. Update Your XML Layout

Replace the default `CurlView` with `ZoomableCurlView` in your XML layout file:

```xml
<com.fahimshariar.pagecurlnub.ZoomableCurlView
    android:id="@+id/curl"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### 2. Initialize in Your Activity

In your activity, reference `ZoomableCurlView` and set up your page provider and other configurations:

```java
ZoomableCurlView mCurlView = findViewById(R.id.curl);
mCurlView.setPageProvider(new PageProvider());
mCurlView.setSizeChangedObserver(new SizeChangedObserver());
// Additional setup if needed
```

### 3. Customize Zoom Levels (Optional)

If you want to customize the zoom limits, modify the `scaleFactor` limits in `ZoomableCurlView.java`:

```java
scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));  // Adjust as needed
```

## Example

Here’s a simple example demonstrating how to use `ZoomableCurlView` in your project:

```java
public class MainActivity extends AppCompatActivity {

    private ZoomableCurlView mCurlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCurlView = findViewById(R.id.curl);
        mCurlView.setPageProvider(new PageProvider());
        mCurlView.setSizeChangedObserver(new SizeChangedObserver());
        mCurlView.setCurrentIndex(0);
    }

    private class PageProvider implements CurlView.PageProvider {
        // Implementation of page provider
    }

    private class SizeChangedObserver implements CurlView.SizeChangedObserver {
        @Override
        public void onSizeChanged(int w, int h) {
            if (w > h) {
                mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
            } else {
                mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
            }
        }
    }
}
```

## Contribution

If you’d like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`.
3. Make your changes and commit them: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature/your-feature-name`.
5. Submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
